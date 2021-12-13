BEGIN{
    FS=","
    fold[1,2]=7
    fold[2,1]=5
}
{
    new_x = $1; new_y=$2;
    printf("From %s-%s", new_x, new_y)
    for (i=1; i<= 2; ++i) {
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
    for (i=0; i<=30; ++i) {
        for (j=0; j<=30; ++j) {
            if (graph[i,j] == 1) {
                printf("#")
            } else {
                printf(".")
            }
        }
        printf("\n")
    }
}