BEGIN{
    count_round = 0;
    count_square = 0;
    count_wedge = 0;
    count_curly = 0;

    total_score = 0;
    FS=""
    pair[")"] = "("
    pair["]"] = "["
    pair[">"] = "<"
    pair["}"] = "{"
    pair["("] = ")"
    pair["["] = "]"
    pair["<"] = ">"
    pair["{"] = "}"

    score[")"] = 1 
    score["]"] = 2 
    score["}"] = 3 
    score[">"] = 4 
}
{
    top = 0;
    line_score = 0;
    for (i=1; i<=NF; ++i) {
        if($i=="(" || $i=="[" || $i=="<" || $i == "{") {
            stack[++top]=$i
        } else {
            if (stack[top] == pair[$i]) {
                --top
            } 
        }
    }
    for(j=top; j>=1; --j) {
        line_score *= 5
        line_score += score[pair[stack[j]]]
    }
    print line_score
}
END{
    #awk -f day10_2.awk day_10_2.txt | sort -n | head -n 23 | tail -n 1
}