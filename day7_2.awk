BEGIN {sum=0; }
{
    sum += $0
    data[NR]=$0
}
END {
for(magic_number = 0; magic_number <= 1911; ++magic_number){
#    left = 0;
 #   right = sum;
    fuel = 0;
    for(i=1; i <= NR; ++i) {
        #left += data[i];
        #right -= data[i];
        #printf("current pos: %s, left draw: %s, right draw: %s, diff: %s \n", data[i], left, right, left-right);
        #856
        if(data[i]>magic_number) {
            step = data[i]-magic_number;
        } else {
            step = magic_number-data[i];
        }
        step_cost = (1+step) * step / 2;
        fuel+=step_cost
               }
    printf("magic_number: %s, cost: %s \n", magic_number, fuel);
    }
    }