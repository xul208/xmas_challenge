BEGIN{
    FS=""
}
{
    for(i = 1; i <= NF; ++i){
    map[NR,i]=$i;
    }
}
END{
    print NR
    print NF
    print map[1,1]
    print map[NR, NF]
    for (i = 1; i<=100;++i) {
        for (j=1; j<=100;++j) {
            current = map[i,j]
            up = map[i-1,j]
            down = map[i+1,j]
            left = map[i,j-1]
            right = map[i,j+1]
            printf("current: %s, up: %s, down: %s, left: %s, right: %s \n",current, up, down, left, right)
            if ((up=="" || up>current)&&(down==""||down>current)&&(left==""||left>current)&&(right==""||right>current)){
                sum+=(current+1)
                print current
            }
        }
    }
    print sum
}