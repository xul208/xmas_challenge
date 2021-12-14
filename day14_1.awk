BEGIN{
    #input="NNCB"
    input="SCSCSKKVVBKVFKSCCSOV"
    split(input, base, "")
    for(i = 1; i <= length(input); ++i) {
        element[base[i]] += 1;
        pairs[base[i]base[i+1]] += 1;
    }
    FS=" -> "
}
{
    rules[$1]=$2
}
END{
    step = 40;
    for (i=1; i<=step; ++i) {
        for (rule in rules){
            if(pairs[rule] > 0) {
                element[rules[rule]] += pairs[rule]

                split(rule, t, "")
                new_pairs[sprintf("%s%s", t[1], rules[rule])] += pairs[rule]
                new_pairs[sprintf("%s%s", rules[rule], t[2])] += pairs[rule]

                pairs[rule] = 0;
            }
        }
        for (new_pair in new_pairs) {
            pairs[new_pair] += new_pairs[new_pair]
            new_pairs[new_pair] = ""
        }
    }

    split("ABCDEFGHIJKLMNOPQRSTUVWXYZ", alphabet, "")
    for(i = 1; i <= 26; ++i) {
        ch = alphabet[i]
        #printf("%s: %s \n", ch, element[ch])
        print element[ch]
    }
    for (pair in pairs) {
#        printf("%s: %s \n", pair, pairs[pair])
    }
#    print "====="
    for (pair in new_pairs) {
#        printf("%s: %s \n", pair, new_pairs[pair])
    }
}