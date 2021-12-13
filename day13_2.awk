BEGIN{
    FS=","
}
{
    graph[$1,$2]=1
}
END{
    for (i=1; i<=100; ++i) {
        for (j=1; j<=100; ++j) {
            if (graph[i,j] == 1) {
                printf("#")
            } else {
                printf(".")
            }
        }
        printf("\n")
    }
}