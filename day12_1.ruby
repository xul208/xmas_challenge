map={}
File.readlines('day12_1.txt').each do |line| 
    entry = line.strip.split('-')
    if map[entry[0]].nil?
        map[entry[0]] = []
    end
    if map[entry[1]].nil?
        map[entry[1]] = []
    end
    map[entry[0]].append entry[1]
    map[entry[1]].append entry[0]
end

def find_num_path(from, to, seen, map)
    if (from == to)
        return 1
    end
    result = 0
    map[from].each do |step|
        printf("check next step %s \n", step)
        if (!seen.key?(step)) 
            if (step == step.downcase) 
                seen[step] = 1;
            end
            result += find_num_path(step, to, seen, map)
            if (step == step.downcase) 
                seen.delete(step)
            end
        else 
            print seen
            print "\n"
        end
    end
    result
end

print map
seen = {}
seen["start"]=1
print find_num_path("start", "end", seen, map)