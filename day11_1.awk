BEGIN{FS=""}
{
    for(i=1;i<=NF;++i){
        memo[NR,i]=$i
    }
}
END{
    step=100;
    for(i=1; i<=step; ++i){
        for(j=1; j<=10; ++j){
            for(k=1; k<=10; ++k) {
                ++memo[j,k]
            }
        }
        for(scan=1; scan<=10; ++scan){
            for(j=1; j<=10; ++j){
                for(k=1; k<=10; ++k) {
                    if(memo[j,k]>=10) {
                        memo[j,k]=0;
                        ++flash
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

        for(j=1; j<=10; ++j){
            for(k=1; k<=10; ++k) {
                printf("%s", memo[j,k])
            }
            printf("\n")
        }
        printf("----------\n")
    }
    print flash
}