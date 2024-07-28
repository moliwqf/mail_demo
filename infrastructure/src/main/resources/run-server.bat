start /d "D:\progrma\Redis-x64-3.0.504" redis-server.exe
start cmd /k "cd /d D:\progrma\nacos-server-2.0.3\nacos\bin && startup.cmd"
start /d "D:\wqfmysql\Navicat Premium 12" navicat.exe
start cmd /k "cd /d D:\progrma\elasticsearch-7.17.3-windows-x86_64\elasticsearch-7.17.3\bin &&elasticsearch.bat"
start cmd /k "cd /d D:\progrma\kibana-7.17.3-windows-x86_64\bin &&kibana.bat"
start cmd /k "cd /d D:\progrma\mongoDB-5.0.25\bin &&mongo.exe" 
start cmd /k "cd /d D:\progrma\logstash-7.17.3\bin &&logstash -f logstash.conf" 
start cmd /k "cd /d D:\progrma\minio &&minio.exe server D:\progrma\minio\data --console-address ":9001"" 






