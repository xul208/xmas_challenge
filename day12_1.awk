# return number of paths between from and to
function dfs (from, to, paths, seen) {
    if (seen[from] == 1) {
        return 0;
    }
    if (from == to) {
        return 1;
    }
    result = 0;
    for(choice in paths) {
        if (seen[choice] == 1) {
            continue;
        }
        else {
            if(tolower(choice)==choice) {
                seen[choice] = 1;
            }
            result += dfs(choice, to, paths, seen);
            if(tolower(choice)==choice) {
                seen[choice] = 0;
            }
        }
    }
    return result;
}

BEGIN{FS="-"}
{
    map[$1,$2]=1; 
    map[$2,$1]=1;
}
END{
    for(a in map) {
        print a
    }
}