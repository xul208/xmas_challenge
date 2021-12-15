row = 0
cave = []
File.readlines('day15_1.txt').each do |line|
#File.readlines('day15_1_example.txt').each do |line|
   cave[row] = line.strip.split('').map{|c| Integer(c)}
   row=row.next
end
memo = {}
cave_map = []
cave_map = Array.new(cave.length){Array.new(cave.length)} 
def shortest_path(cave, start_y, start_x, memo, cave_map)
    hash_key = start_y*1000+start_x

    if memo.has_key? hash_key
        return memo[hash_key]
    end

    len_y = cave.length
    len_x = cave[0].length

    if (start_y == len_y-1 && start_x == len_x-1)
        return 0
    end

    result = 1<<16
    if (start_x < len_x-1) 
        result = [result, cave[start_y][start_x+1] + shortest_path(cave, start_y, start_x+1, memo, cave_map)].min
    end
    if (start_y < len_y-1) 
        result = [result, cave[start_y+1][start_x] + shortest_path(cave, start_y+1, start_x, memo, cave_map)].min
    end
    memo[hash_key] = result
    printf("Y: %s, X: %s , result: %s\n", start_y, start_x, result)
    cave_map[start_y][start_x] = result + cave[start_y][start_x]
    result
end

print shortest_path(cave, 0, 0, memo, cave_map)
printf ("\n")
print cave_map