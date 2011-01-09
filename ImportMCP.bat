@echo off
echo Importing MCP 2.6 Test 4
python lib/scripts/ImportMCP.py
copy mappings.yml data\server\1.1_02\
ant full-setup
java -Xmx1024M -Xms1024M -jar staged/middlecraft.jar GetServerInterfaces
pause