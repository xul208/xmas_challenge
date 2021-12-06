BEGIN {
}
{
    if(NR == 1){
        split($0, draws, ",")
        for(i=1; draws[i]!=""; ++i) {
            draws_rank[draws[i]]=i
        }    }
    else if($0==""){
        total_count++
        row_count=1
    } else {
        split($0, numbers, " ")
        for (i = 1; i <= 5; ++i) {
            boards[total_count, row_count, i] = numbers[i]
        }
        ++row_count
    }    }
END{
    min_draw = 0
    min_board = 0;
    for(board = 87; board <= 87; ++board) {
        board_min_draw = 100;
        for (row = 1; row<=5; ++row) {
            row_max_draw = 0;
            for (col = 1; col <= 5; ++col){
                current_num = boards[board, row, col]
                if(draws_rank[current_num] > row_max_draw){
                    row_max_draw = draws_rank[current_num]
                }
            }
            printf("row_max_draw: %s\n", row_max_draw)
            if (row_max_draw < board_min_draw) {
                board_min_draw = row_max_draw
            }
        }
        for (col = 1; col <= 5; ++col){
            col_max_draw = 0;
            for (row = 1; row <= 5; ++row){
                current_num = boards[board, row, col]
                if(draws_rank[current_num] > col_max_draw){
                    col_max_draw = draws_rank[current_num]
                }
            }
            printf("col_max_draw: %s\n", col_max_draw)
            if (col_max_draw < board_min_draw) {
                board_min_draw = col_max_draw
            }
        }

        if(board_min_draw > min_draw) {
            min_draw = board_min_draw
            min_board = board

        }
    }
    print "-------"
    print min_board
    print "-------"
print min_draw
print "===="
    sum = 0;
    for (i = 1; i<=5; ++i) {
        for (j=1; j<=5; ++j){
            if (draws_rank[boards[min_board,i,j]] > 89) {
                sum+=boards[min_board,i,j]
            }
        }
        print "===="
    }
    print sum * draws[89]
}