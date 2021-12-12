map={}
seen={}
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
    seen[entry[0]]=0
    seen[entry[1]]=0
end

def find_num_path(from, to, seen, map, record)
    if (from == to)
        print record
        print "\n"
        return 1
    end
    result = 0
    map[from].each do |step|
        if (step == 'start') 
            next
        end
        max_seen = seen.values.max
        print max_seen
        if (step==step.upcase || max_seen < 2 || seen[step] < 1) 
            if (step == step.downcase) 
                seen[step] += 1;
            end
            record.push step
            result += find_num_path(step, to, seen, map, record)
            record.pop
            if (step == step.downcase) 
                seen[step] -= 1;
            end
        else 
        end
    end
    result
end

find_num_path("start", "end", seen, map, [])