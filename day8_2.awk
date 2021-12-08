BEGIN{FS=" "}
{
    for (i=0; i<=9; ++i) {
        dict[$i]="";
    }
    for (i=1; i<=10; ++i) {
        if (length($i) == 2) {
            dict[1]=$i
        } else if (length($i)==3) {
            dict[7]=$i
        } else if (length($i)==4) {
            dict[4]=$i
        } else if (length($i)==7) {
            dict[8]=$i
        }
    }
    split(dict[1], one_chars, "")
    split(dict[4], four_chars, "")
    split(dict[7], seven_chars, "")
    unique_count = 1;
    for (i = 1; i <= 4; ++i) {
        if(four_chars[i] != one_chars[1] && four_chars[i]!=one_chars[2]) {
            four_unique[unique_count++]=four_chars[i];
        }
    }
    for (i=1; i<=10; ++i) {
        if (length($i) == 5) {
            if (index($i, one_chars[1]) >0 && index($i, one_chars[2])>0) {
                dict[3]=$i;
            }
            else if(index($i, four_unique[1])>0 && index($i, four_unique[2])>0){
                dict[5]=$i;
            } else {
                dict[2]=$i;
            }
        } 
        if (length($i) == 6) {
            if(index($i,four_chars[1])>0 && index($i, four_chars[2])>0 && index($i, four_chars[3])>0 && index($i, four_chars[4])>0 ){
                dict[9]=$i;
            } else if(index($i, seven_chars[1])>0 && index($i, seven_chars[2])>0 && index($i, seven_chars[3])>0) {
                dict[0]=$i;
            } else {
                dict[6]=$i;
            }
        }
    }
    for (j=0;j<=9;++j){
        printf("%s ", dict[j])
    }
    printf("\n")
    result = 0;
    for (i=12; i<=NF; ++i) {
        printf("%s ", $i)
        for (j=0;j<=9;++j){
            if (length(dict[j])!=length($i)) {
                continue;
            }
            hit=0;
            for (k=1; k<=length(dict[j]); ++k) {
                ch = substr(dict[j], k, 1)
                if(index($i, ch) > 0) {
                    ++hit;
                }
            }
            if (hit == length($i)){
                result *= 10;
                result += j;
                break;
            }
        }
    }
    print result;
    sum+=result;
}
END{
    print "======"
    print sum
}