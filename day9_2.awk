BEGIN{
    FS=""
}
{
    for(i = 1; i <= NF; ++i){
        map[NR,i]=$i;
    }
}
END{
    for (i = 1; i<=NR;++i) {
        for (j=1; j<=NF;++j) {
            current = map[i,j]
            up = map[i-1,j]
            down = map[i+1,j]
            left = map[i,j-1]
            right = map[i,j+1]
            #printf("current: %s, up: %s, down: %s, left: %s, right: %s \n",current, up, down, left, right)
            if ((up=="" || up>current)&&(down==""||down>current)&&(left==""||left>current)&&(right==""||right>current)){
                sum+=(current+1)

                lows[++lows_count,1]=i;
                lows[lows_count,2]=j;
            }
        }
    }
    for(i=1; i<=lows_count; ++i) {
        head = 1;
        queue[1,1]=lows[i,1];queue[1,2]=lows[i,2];
        seen[i,lows[i,1], lows[i,2]]="true"
        tail=1;
        basin_area = 0;
        while(head <= tail) {
            #printf("head %s, tail %s\n", head, tail);
            #   printf("head_y %s, head_x %s\n", queue[head,1], queue[head,2]);
            current_node = map[queue[head,1],queue[head,2]]
            #printf("-----------current_node %s\n", current_node);
            up_node = map[queue[head,1]-1,queue[head,2]]
            down_node = map[queue[head,1]+1,queue[head,2]]
            left_node = map[queue[head,1],queue[head,2]-1]
            right_node = map[queue[head,1],queue[head,2]+1]
            #printf("u: %s, d: %s, l: %s, r: %s\n", up_node, down_node, left_node, right_node);

            if (current_node!=9){
                if(up_node!="" && seen[i,queue[head,1]-1, queue[head,2]]!="true"){
                    queue[++tail,1]=queue[head,1]-1;queue[tail,2]=queue[head,2];
                    #printf("1enqueue y:%s, x:%s, %s\n", queue[head,1]-1, queue[head,2], up_node);
                    seen[i,queue[head,1]-1, queue[head,2]]="true"
                }
                if(down_node!="" && seen[i,queue[head,1]+1, queue[head,2]]!="true"){
                    queue[++tail,1]=queue[head,1]+1;queue[tail,2]=queue[head,2];
                    #printf("2enqueue y:%s, x:%s, %s\n", queue[head,1]+1, queue[head,2],down_node);
                    seen[i,queue[head,1]+1, queue[head,2]]="true"
                }
                if(left_node!="" && seen[queue[head,1], queue[head,2]-1]!="true"){
                    queue[++tail,1]=queue[head,1];queue[tail,2]=queue[head,2]-1;
                    #printf("3enqueue y:%s, x:%s, %s\n", queue[head,1], queue[head,2]-1,left_node);
                    seen[i,queue[head,1], queue[head,2]-1]="true"
                }
                if(right_node!="" && seen[queue[head,1], queue[head,2]+1]!="true"){
                    queue[++tail,1]=queue[head,1];queue[tail,2]=queue[head,2]+1;
                    #printf("4enqueue y:%s, x:%s, %s\n", queue[head,1], queue[head,2]+1,right_node);
                    seen[i,queue[head,1], queue[head,2]+1]="true"
                }
                map[queue[head,1],queue[head,2]]=""
                basin_area++;
                # printf("=========basin_area %s\n", basin_area);
            }
            head++;
        }
        basins[basin_count++]=basin_area
        print basin_area
    }
}