dict = {'0' => '0000', '1' => '0001', '2' => '0010', '3' => '0011', '4' => '0100',
'5' => '0101', '6' => '0110', '7' => '0111', '8' => '1000', '9' => '1001',
'A' => '1010', 'B' => '1011', 'C' => '1100', 'D' => '1101', 'E' => '1110',
'F' => '1111'}

$input = '8A004A801A8002F478'
$offset = 0
$versions = []
$types = []

raw_binary_str = $input.split('').map{ |x| dict[x] }.join('').split('')

def parse_int (raw, step)
    result = 0
    for i in 1..step do
        result *= 2
        if (raw[$offset] == '0')
            result += 0
        else
            result += 1
        end
        $offset = $offset.next
    end
    return result
end

def parse_version(raw)
    $versions.push(parse_int(raw_binary_str, 3))
    parse_type()
end

def parse_type(raw)
    $ersions.pus
end
