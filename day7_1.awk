BEGIN { FS=",";}
{
    for(i=1; i<= NF; ++i){
        data[i]=$i;
    }}
END {
    for ( i in data )
        print data[i]
    # | sort -nr
}