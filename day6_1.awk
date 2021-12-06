BEGIN { FS=","}
{
    for (i = 1; i <= NF; ++i) {
        day[NR,i]=$i
    }}
END{
    for (i = 2; i <= 257; ++i) {
        baby_count = 0;
        for (j = 1; day[i-1, j]!=""; ++j) {
            if(day[i-1,j] == 0) {
                day[i,j] = 6;
                baby_count++;
            }
            else{
                day[i,j] = day[i-1,j]-1;
            }
        }
        for(k=j;k<=baby_count-1 + j;++k){
            day[i,k]=8;
        }
        printf("day %s, baby count: %s \n",i, baby_count);
    }
    for (i=1; day[257,i]!="";++i){
    }
    print i-1;
}