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
                # second question, BFS
                map[i,j]=""
                head = 1; 
                queue[1][1]=i-1;queue[1][2]=j;
                queue[2][1]=i+1;queue[1][2]=j;
                queue[3][1]=i;queue[3][2]=j-1;
                queue[4][1]=i;queue[4][2]=j+1;
                tail=4;
                basin_area = 0;
                while(head <= tail) {
                    current = map[i,j]
                    up = map[i-1,j]
                    down = map[i+1,j]
                    left = map[i,j-1]
                    right = map[i,j+1]
                }
            }

        }
    }
    print sum
}