require 'set'

row = 0
cave = []
#File.readlines('day15_1.txt').each do |line|
File.readlines('day15_1_example.txt').each do |line|
  cave[row] = line.strip.split('').map { |c| Integer(c) }
  row = row.next
end
cave_map = Array.new(cave.length) { Array.new(cave.length) }

def shortest_path(cave, start_y, start_x, cave_map, seen)
  printf("checking Y: %s, X: %s \n", start_y, start_x)
  unless cave_map[start_y][start_x].nil?
    return cave_map[start_y][start_x]
  end

  len_y = cave.length
  len_x = cave[0].length

  next_cost = 1 << 16
  potential_next_costs = []
  up_key = (start_y - 1) * 1000 + start_x
  down_key = (start_y + 1) * 1000 + start_x
  left_key = start_y * 1000 + start_x - 1
  right_key = start_y * 1000 + start_x + 1

  if start_x < len_x - 1 && !seen.include?(right_key)
    seen.add right_key
    potential_next_costs.push cave[start_y][start_x + 1] + shortest_path(cave, start_y, start_x + 1, cave_map, seen)
    seen.remove right_key
  end
  if start_y < len_y - 1 && !seen.include?(down_key)
    seen.add down_key
    potential_next_costs.push cave[start_y + 1][start_x] + shortest_path(cave, start_y + 1, start_x, cave_map, seen)
    seen.remove down_key
  end
  if start_x > 0 && !seen.include?(left_key)
    seen.add left_key
    potential_next_costs.push cave[start_y][start_x - 1] + shortest_path(cave, start_y, start_x - 1, cave_map, seen)
    seen.remove left_key
  end
  if start_y > 0 && !seen.include?(up_key)
    seen.add up_key
    potential_next_costs.push cave[start_y - 1][start_x] + shortest_path(cave, start_y - 1, start_x, cave_map, seen)
    seen.remove up_key
  end
  if start_x == len_x - 1 && start_y == len_y - 1
    potential_next_costs.push 0
  end
  next_cost = potential_next_costs.min
  cave_map[start_y][start_x] = next_cost
  next_cost
end

shortest_path(cave, 0, 0, cave_map, Set.new)

=begin
(0..cave_map.length - 1).each { |i|
  (0..cave_map[0].length - 1).each { |j|
    printf("%s,", cave_map[i][j])
  }
  print "\n"
}
=end