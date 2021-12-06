BEGIN { FS=","}
{
    for (j=0; j<=8; ++j){
        day[NR,j]=0;
    }
    for (i = 1; i <= NF; ++i) {
        day[NR,$i]+=1
    }
}
END{
    for (i = 2; i <= 257; ++i) {
        day[i,8]=day[i-1,0];
        day[i,7]=day[i-1,8];
        day[i,6]=day[i-1,0] + day[i-1,7];
        for (j=5; j>=0; j--){
            day[i,j]=day[i-1,j+1];
        }
        printf("day %s :", i);
        for (j=0; j<=8; ++j){
            printf("%s ", day[i,j]);
        }
        printf("\n")
    }
    sum=0;
    for (i=0; i<=8;++i){
        sum+=day[257,i];
    }
    print sum;
}