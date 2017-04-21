/*
Flower-bed : LinkedIn

*/

public static boolean canPlant(boolean[] arr, int count){
    if(arr == null){
        throw new NullPointerException();
    }

    int locations = 0; // find possible locations to plant 
    boolean lastPos = false, thisPos = false, nextPos = arr[0];
    int index = 1;
    while(index < arr.length){
        lastPos == thisPos;
        thisPos = nextPos;
        nextPos = arr[index];
        if(!(lastPos || thisPos || nextPos)){
            locations ++
            if(locations >= count){
                return true;
            }
        }

        index++;
    }
    if(index == arr.length && !(thisPos || nextPos)){
        locations++;
    }
    return locations <= count;
}