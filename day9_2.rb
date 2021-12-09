floor_map = []
File.readlines('day9_1.txt').each do |line|
    chars = line.split('')
    ints = chars.map{|x| x.to_i}
    floor_map.push(ints)
end
sum=0
for i in 0..99 do
    for j in 0..99 do 
        current = floor_map[i]&.dig(j)
        down = floor_map[i + 1]&.dig(j)
        up = floor_map[i - 1]&.dig(j)
        left = floor_map[i]&.dig(j-1)
        right = floor_map[i]&.dig(j-1)
        if ((up == nil || up>current)&&
            (down==nil||down>current)&&
            (left==nil||left>current)&&
            (right==nil||right>current)) 
            sum+=(current+1)
            printf("current: %s, i: %s, j: %s \n",current, i,j)
        end 
    end
end
print sum