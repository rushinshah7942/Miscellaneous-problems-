/*

Producer–consumer_problem

*/
/*

To solve the problem, a less experienced programmer might come up with a solution shown below. 

In the solution two library routines are used, sleep and wakeup. When sleep is called, the caller is blocked until another process wakes it up by using the wakeup routine. 

The global variable itemCount holds the number of items in the buffer.

*/

int itemCount = 0;

procedure producer() {
    while (true) {
        item = produceItem();

        if (itemCount == BUFFER_SIZE) {
            sleep();
        }

        putItemIntoBuffer(item);
        itemCount = itemCount + 1;

        if (itemCount == 1) {
            wakeup(consumer);
        }
    }
}

procedure consumer() {
    while (true) {

        if (itemCount == 0) {
            sleep();
        }

        item = removeItemFromBuffer();
        itemCount = itemCount - 1;

        if (itemCount == BUFFER_SIZE - 1) {
            wakeup(producer);
        }

        consumeItem(item);
    }
}
// Race condition - Deadlock situation 

/*

A race condition is an undesirable situation that occurs when a device or system attempts to perform two or more operations at the same time, but because of the nature of the device or system, the operations must be done in the proper sequence in order to be done correctly.

Java
----------
A race condition is a situation in which two or more threads or processes are reading or writing some shared data, and the final result depends on the timing of how the threads are scheduled. Race conditions can lead to unpredictable results and subtle program bugs.

*/

The problem with this solution is that it contains a race condition that can lead to a deadlock. Consider the following scenario:

1. The consumer has just read the variable itemCount, noticed it's zero and is just about to move inside the if block.
2. Just before calling sleep, the consumer is interrupted and the producer is resumed.
3. The producer creates an item, puts it into the buffer, and increases itemCount.
4. Because the buffer was empty prior to the last addition, the producer tries to wake up the consumer.
5. Unfortunately the consumer wasn't yet sleeping, and the wakeup call is lost. When the consumer resumes, it goes to sleep and will never be awakened again. This is because the consumer is only awakened by the producer when itemCount is equal to 1.
6. The producer will loop until the buffer is full, after which it will also go to sleep.

Since both processes will sleep forever, we have run into a deadlock. This solution therefore is unsatisfactory.

An alternative analysis is that if the programming language does not define the semantics of concurrent accesses to shared variables (in this case itemCount) without use of synchronization, then the solution is unsatisfactory for that reason, without needing to explicitly demonstrate a race condition.

// Solution - Using semaphores

Semaphores solve the problem of lost wakeup calls. In the solution below we use two semaphores, fillCount and emptyCount, to solve the problem. fillCount is the number of items already in the buffer and available to be read, while emptyCount is the number of available spaces in the buffer where items could be written. fillCount is incremented and emptyCount decremented when a new item is put into the buffer. If the producer tries to decrement emptyCount when its value is zero, the producer is put to sleep. The next time an item is consumed, emptyCount is incremented and the producer wakes up. The consumer works analogously.


/// ------------------------------------------------------
semaphore fillCount = 0; // items produced
semaphore emptyCount = BUFFER_SIZE; // remaining space

procedure producer() {
    while (true) {
        
		item = produceItem();
        
		down(emptyCount);
        putItemIntoBuffer(item);
        up(fillCount);
    }
}

procedure consumer() {
    while (true) {
        
		down(fillCount);
        item = removeItemFromBuffer();
        up(emptyCount);
        
		consumeItem(item);
    }
}

The solution above works fine when there is only one producer and consumer. With multiple producers sharing the same memory space for the item buffer, or multiple consumers sharing the same memory space, this solution contains a serious race condition that could result in two or more processes reading or writing into the same slot at the same time. To understand how this is possible, imagine how the procedure putItemIntoBuffer() can be implemented. It could contain two actions, one determining the next available slot and the other writing into it. 

If the procedure can be executed concurrently by multiple producers, then the following scenario is possible:
1. Two producers decrement emptyCount
2. One of the producers determines the next empty slot in the buffer
3. Second producer determines the next empty slot and gets the same result as the first producer
4. Both producers write into the same slot

To overcome this problem, we need a way to make sure that only one producer is executing putItemIntoBuffer() at a time. In other words, we need a way to execute a critical section with mutual exclusion. 


mutex buffer_mutex; // similar to "semaphore buffer_mutex = 1", but different (see notes below)
semaphore fillCount = 0;
semaphore emptyCount = BUFFER_SIZE;

procedure producer() {
    while (true) {
        item = produceItem();
 
		down(emptyCount);  // P
            down(buffer_mutex); // P
                putItemIntoBuffer(item);
            up(buffer_mutex); // V
        up(fillCount); // V
    }
}

procedure consumer() {
    while (true) {
        
		down(fillCount);
            down(buffer_mutex);
                item = removeItemFromBuffer();
            up(buffer_mutex);
        up(emptyCount);
        
		consumeItem(item);
    }
}

// Monitors
// https://en.wikipedia.org/wiki/Monitor_(synchronization)
In concurrent programming, a monitor is a synchronization construct that allows threads to have both mutual exclusion and the ability to wait (block) for a certain condition to become true. Monitors also have a mechanism for signalling other threads that their condition has been met. A monitor consists of a mutex (lock) object and condition variables. A condition variable is basically a container of threads that are waiting for a certain condition. Monitors provide a mechanism for threads to temporarily give up exclusive access in order to wait for some condition to be met, before regaining exclusive access and resuming their task.


monitor ProducerConsumer {
    int itemCount;
    condition full;
    condition empty;

    procedure add(item) {
        while (itemCount == BUFFER_SIZE) {
            wait(full);
        }

        putItemIntoBuffer(item);
        itemCount = itemCount + 1;

        if (itemCount == 1) {
            notify(empty);
        }
    }
    procedure remove() {
        while (itemCount == 0) {
            wait(empty);
        }

        item = removeItemFromBuffer();
        itemCount = itemCount - 1;

        if (itemCount == BUFFER_SIZE - 1) {
            notify(full);
        }


        return item;
    }
}

procedure producer() {
    while (true) {
        item = produceItem();
        ProducerConsumer.add(item);
    }
}

procedure consumer() {
    while (true) {
        item = ProducerConsumer.remove();
        consumeItem(item);
    }
}