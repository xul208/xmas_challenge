BEGIN{FS=" "}
{
    for(i = 1; i <= NF; ++i) {
        if ($i == "|") {
            has_pipe = true;
            continue;
        }
        if (i > 11 && length($i)!=5 && length($i)!=6) {
            printf ("%s ",$i)
            ++sum
        }
    }
    printf ("\n")
}
END{
print sum;
}