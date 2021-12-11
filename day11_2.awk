BEGIN{FS=""}
{
    for(i=1;i<=NF;++i){
        memo[NR,i]=$i
    }
}
END{
    step=193;
    for(i=1; i<=step; ++i){
        for(j=1; j<=10; ++j){
            for(k=1; k<=10; ++k) {
                ++memo[j,k]
            }
        }
        round_flash=0;
        for(scan=1; scan<=10; ++scan){
            for(j=1; j<=10; ++j){
                for(k=1; k<=10; ++k) {
                    if(memo[j,k]>=10) {
                        memo[j,k]=0;
                        ++flash
                        ++round_flash
                        if(memo[j-1, k]!=0) ++memo[j-1,k]; 
                        if(memo[j+1, k]!=0) ++memo[j+1,k]; 
                        if(memo[j,k-1]!=0) ++memo[j,k-1]; 
                        if(memo[j,k+1]!=0) ++memo[j,k+1];
                        if(memo[j-1,k-1]!=0) ++memo[j-1,k-1]; 
                        if(memo[j+1,k+1]!=0) ++memo[j+1,k+1]; 
                        if(memo[j+1,k-1]!=0) ++memo[j+1,k-1]; 
                        if(memo[j-1,k+1]!=0) ++memo[j-1,k+1];
                    }
                }
            }
        }
        if(round_flash==100 && sync_round=="") sync_round=i;
        for(j=1; j<=10; ++j){
            for(k=1; k<=10; ++k) {
                printf("%s", memo[j,k])
            }
            printf("\n")
        }
        printf("----------round_flash %s\n", round_flash)
    }
    print flash
    print sync_round
}