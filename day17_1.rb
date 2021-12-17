min_x = 20
max_x = 30
min_y = -10
max_y = -5

max_height = 0
v_x = 1
while v_x < max_x do
  v_y = 1
  while v_y < max_x do
    simulation_max_h = 0;
    hit_target = false
    x = 0
    y = 0
    t = 0
    while y > min_y do
      x = x + v_x
      y = y + v_y
      printf("time: %d \n", t)
      printf("velocity: v_x %d, v_y %d\n", v_x, v_y)
      printf("pos: x %d, y %d\n", x, y)

      simulation_max_h = [simulation_max_h, y].max
      v_x -= 1 unless v_x == 0
      v_y -= 1
      if x >= min_x && x <= max_x && y >= min_y && y <= max_y
        hit_target = true
        printf(">>>>>>>>>>>>>>>>> Hit at time %s <<<<<\n", t)
      end
      t = t + 1
      printf("----------------time: %d \n", t)
      printf("velocity: v_x %d, v_y %d\n", v_x, v_y)
      printf("pos: x %d, y %d\n", x, y)
    end
    #printf("simulation highest: %s\n", simulation_max_h)
    max_height = [max_height, simulation_max_h].max if hit_target
    v_y = v_y + 1
  end
  v_x = v_x + 1
end

print max_height
