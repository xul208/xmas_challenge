BEGIN { FS=",| -> " }
{
    print $1, $2, $3, $4
    if($1 == $3 && $2 < $4) {
        for (i = $2; i <= $4; ++i) {
            map[$1,i] += 1;
        }
        printf("marked %s points horizontally \n", i - $2)
    }

    else if($1 == $3 && $2 > $4) {
        for (i = $4; i <= $2; ++i) {
            map[$1,i] += 1;
        }
        printf("marked %s points horizontally \n", i-$1)
    }

    else if ($2 == $4 && $1 < $3) {
        for (i = $1; i <= $3; ++i) {
            map[i, $2]+=1;
        }
        printf("marked %s points vertically \n", i-$2)
    }
    else if ($2 == $4 && $3 < $1) {
        for (i = $3; i <= $1; ++i) {
            map[i, $2]+=1;
        }
        printf("marked %s points vertically \n", i-$3)
    }
    else {
        diag_count = 0
        for (i = $1; i != $3;) {
            for (j = $2; j != $4;) {
                map[i,j]+=1;
                if ($1 < $3) {i++}
                if ($1 > $3){i--}
                if ($2 < $4){j++}
                if ($2 > $4){j--}
                diag_count++
            }
        }
        map[$3,$4]+=1
        printf("marked %s points diagonally \n", diag_count+1)
    }}
END {
    for (i = 0; i <= 1000; ++i) {
        for (j =0; j <= 1000; ++j ){
            if (map[i, j] > 1) {
              #  printf("map[%s, %s] is %s\n", i,j,map[i,j])
                count++;
            }
        }    }
    print count
}