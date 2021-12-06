BEGIN {
  total_count = 0;
}
{
  raw_input[++total_count]=$0
}
END {
  for (i = 1; i <= 12; ++i) {
    delete left;
    delete right;
    left_count = 0;
    right_count = 0;

    for (j=1; j <= total_count; ++j) {
      split(raw_input[j],bits,"");
      if(bits[i]==1) {
        left[++left_count] = raw_input[j];
      } else {
        right[++right_count] = raw_input[j];
      }     
    }
    printf("left count: %s, right count: %s \n", left_count, right_count);
    delete raw_input;
    if(left_count < right_count) {
      if (left_count==1) {
        print left[1];
        break;
      }
      for (k=1; k<=left_count; ++k) {
        raw_input[k]=left[k];
        total_count=left_count;
      } 
    } else {
      if (right_count==1) {
        print right[1];
      }
      for (k=1; k<=right_count; ++k) {
        raw_input[k]=right[k];
        total_count=right_count;
      } 
    }
  }
}
