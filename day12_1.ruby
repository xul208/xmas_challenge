map = {}
File.readlines('day12_1.txt').each do |line|
  entry = line.strip.split('-')
  map[entry[0]] = [] if map[entry[0]].nil?
  map[entry[1]] = [] if map[entry[1]].nil?
  map[entry[0]].append entry[1]
  map[entry[1]].append entry[0]
end

def find_num_path(from, to, seen, map)
  return 1 if (from == to)
  result = 0
  map[from].each do |step|
    printf("check next step %s \n", step)
    if (!seen.key?(step))
      seen[step] = 1 if (step == step.downcase)
      result += find_num_path(step, to, seen, map)
      seen.delete(step) if (step == step.downcase)
    else
      print seen
      print "\n"
    end
  end
  result
end

print map
seen = {}
seen['start'] = 1
print find_num_path('start', 'end', seen, map)
