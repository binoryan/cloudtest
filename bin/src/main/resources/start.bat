cmd /C start/MIN java -jar %1 -role hub

cmd /C start/MIN java -Dwebdriver.chrome.driver="%2" -jar %1 -role node -browser "browserName=chrome,version=80.0" -hub http://localhost:4444/grid/register