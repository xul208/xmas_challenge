BEGIN {sum=0; }
{
    if ($0 >= 345) {
        diff = $0-345;
    } else {
        diff = 345-$0;

    }       sum += diff
}
END {
    print sum;
}