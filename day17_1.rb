min_x = 20
max_x = 30
min_y = -10
max_y = -5

max_height = 0
v_x = 1
(1...151).each { |v_x|
  (1...500).each { |v_y|
    v_x_initial = v_x
    v_y_initial = v_y
    if v_x == 7 && v_y == 2
      debug = true
    end
   simulation_max_h = 0
    hit_target = false
    x = 0
    y = 0
    t = 1
    while y >= min_y && x <= max_x do
      x = x + v_x
      y = y + v_y

      if debug
        printf("time: %d \n", t)
        printf("velocity: v_x %d, v_y %d\n", v_x, v_y)
        printf("pos: x %d, y %d\n", x, y)
      end

      simulation_max_h = [simulation_max_h, y].max
      v_x -= 1 unless v_x == 0
      v_y -= 1
      if x >= min_x && x <= max_x && y >= min_y && y <= max_y
        hit_target = true
        printf(">>>>>>>>>>>>>>>>> Hit at time %s at x %d y %d<<<<<<<<<<<<<<\n", t, x, y)
        printf("initial speed x: %d, y: %d\n", v_x_initial, v_y_initial)
        break
      end
      t += 1
    end
    max_height = [max_height, simulation_max_h].max if hit_target
    printf("simulation highest: %s\n", simulation_max_h) if hit_target
  }
}

print max_height
