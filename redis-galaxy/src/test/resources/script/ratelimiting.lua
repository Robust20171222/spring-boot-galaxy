-- 调用redis的incr命令给接收验证码的手机号记录1次访问次数
local times = redis.call('incr', KEYS[1])
-- 如果返回1，则说明该手机号是第一次接收验证码，接着给该手机号设置有效接收时间
if times == 1 then
    -- 说明刚创建, 设置生存时间
    redis.call('expire', KEYS[1], ARGV[1])
end

-- 如果redis中的记录次数大于每个手机号接收验证码的次数，那么返回0，否则返回1
if times > tonumber(ARGV[2]) then
    return 0
end
return 1