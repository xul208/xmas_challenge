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

    score[")"] = 3 
    score["]"] = 57 
    score["}"] =  1197 
    score[">"] = 25137 
}
{
    top = 0;
    for (i=1; i<NF; ++i) {
        if($i=="(" || $i=="[" || $i=="<" || $i == "{") {
            stack[++top]=$i
        } else {
            if (stack[top] == pair[$i]) {
                top--
            } else {
                total_score += score[$i]
                next
            }
        }
    }
    # create input for question 2
    #print
}
END{
    print total_score
}