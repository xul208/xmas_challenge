map = {}
seen = {}
File.readlines('day12_1.txt').each do |line|
  entry = line.strip.split('-')
  map[entry[0]] = [] if map[entry[0]].nil?
  map[entry[1]] = [] if map[entry[1]].nil?
  map[entry[0]].append entry[1]
  map[entry[1]].append entry[0]
  seen[entry[0]] = 0
  seen[entry[1]] = 0
end

def find_num_path(from, to, seen, map, record)
  if (from == to)
    print record
    print "\n"
    return 1
  end
  result = 0
  map[from].each do |step|
    next if (step == 'start')
    max_seen = seen.values.max
    print max_seen
    if (step == step.upcase || max_seen < 2 || seen[step] < 1)
      seen[step] += 1 if (step == step.downcase)
      record.push step
      result += find_num_path(step, to, seen, map, record)
      record.pop
      seen[step] -= 1 if (step == step.downcase)
    else

    end
  end
  result
end

find_num_path('start', 'end', seen, map, [])
