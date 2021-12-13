BEGIN{
    FS=","
    fold[1,1]=655
    fold[2,2]=447
    fold[3,1]=327
    fold[4,2]=223
    fold[5,1]=163
    fold[6,2]=111
    fold[7,1]=81
    fold[8,2]=55
    fold[9,1]=40
    fold[10,2]=27
    fold[11,2]=13
    fold[12,2]=6
}
{
    new_x = $1; new_y=$2;
    printf("From %s-%s", new_x, new_y)
    for (i=1; i<= 12; ++i) {
        if(fold[i,1]!="" && fold[i,1] < new_x) {
            new_x = 2*fold[i,1] - new_x; 
        }
        else if(fold[i,2]!="" && fold[i,2] < new_y) {
            new_y = 2*fold[i,2] - new_y;
        }
    } 
    printf(" to %s-%s\n", new_x, new_y)
    graph[new_y,new_x]=1
}
END{
    for (i=0; i<=100; ++i) {
        for (j=0; j<=100; ++j) {
            if (graph[i,j] == 1) {
                printf("#")
            } else {
                printf(".")
            }
        }
        printf("\n")
    }
}