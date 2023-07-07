
echo off
set mysql_dir=D:\Vtiger\vtigercrm-5.4.0\mysql
set mysql_username=root
set mysql_password=root
set mysql_port=33307
set database_name=vtigercrm540
set mysql_bundled=true
set apache_dir=D:\Vtiger\vtigercrm-5.4.0\apache
set apache_bin=D:\Vtiger\vtigercrm-5.4.0\apache\bin
set apache_conf=D:\Vtiger\vtigercrm-5.4.0\apache\conf
set apache_port=8888
set apache_service=true
set apache_bundled=true


echo %WINDIR%
set FIND_STR="%WINDIR%\system32\findstr.exe"
set SLEEP_STR="%WINDIR%\system32\ping.exe"
goto initiate

:initiate
rem if true means , vtiger crm mysql is being used
if "test" == "%1test" goto start1
set VTIGER_HOME=%1
goto start2

:start1
cd ..
set VTIGER_HOME=%cd%
:start2

if %apache_bundled% == true goto checkBundledApache
if %apache_bundled% == false goto checkUserApache

:checkBundledApache
echo "APACHEBUNDLED"
cd /d %apache_dir%
if %apache_service% == true goto StartApacheService
start bin\apache -f conf\httpd.conf
IF ERRORLEVEL 1 goto stopservice
goto checkmysql

:StartApacheService
echo ""
echo "making an attempt to kill any existing vtigercrm service"
echo ""
bin\apache -k stop -n vtigercrmApache540
bin\apache -k uninstall -n vtigercrmApache540
echo "Uninstalling apache service again for confirmation after sleeping for 5 seconds"
echo ""
%SLEEP_STR% -n 5 127.0.0.1>nul
bin\apache -k stop -n vtigercrmApache540
bin\apache -k uninstall -n vtigercrmApache540
echo ""
echo ""
echo "Installing  vtigercrmApache540 APACHE service after sleeping for 5 seconds"
echo ""
%SLEEP_STR% -n 5 127.0.0.1>nul
bin\apache -k install -n vtigercrmApache540 -f conf\httpd.conf
echo ""
echo "Starting  vtigercrmApache540 apache service"
echo ""
bin\apache -n vtigercrmApache540 -k start
IF ERRORLEVEL 1 goto stopservice
goto checkmysql

:checkUserApache
netstat -anp tcp >port.txt
%FIND_STR% "\<%apache_port%\>" port.txt
if ERRORLEVEL 1 goto apachenotrunning
%FIND_STR% "\<%apache_port%\>" port.txt >list.txt
%FIND_STR% "LISTEN.*" list.txt
if ERRORLEVEL 1 goto apachenotrunning
echo ""
echo "Apache is running"
echo ""
goto checkmysql

:apachenotrunning
echo ""
echo ""
echo "Apache in the location %apache_dir% is not running. Start Apache and then start vtiger crm"
echo ""
echo ""
set /p pt=Press Any Key to Continue...
goto end

:checkmysql
cd /d %mysql_dir%\bin
echo %cd%

echo ""
echo "Checking whether the MySQL server is already running"
echo ""
mysql --port=%mysql_port% --user=%mysql_username% --password=%mysql_password% -e "show databases" > NUL
IF ERRORLEVEL 1 goto startmysql
echo ""
echo ""
ECHO  "MySQL is already started and running"
echo ""
echo ""
goto checkdatabase

:startmysql
echo ""
echo "Starting MySQL on port specified by the user"
echo ""
mysqld-nt  --install vtigercrmMysql540 --defaults-file="%mysql_dir%\my.ini"
net start vtigercrmMysql540
start mysqld-nt -b .. --skip-bdb --log-queries-not-using-indexes --log-slow-admin-statements --log-error --low-priority-updates --log-slow-queries=vtslowquery.log --default-storage-engine=InnoDB --datadir=../data --port=%mysql_port%
%SLEEP_STR% -n 11 127.0.0.1>nul
echo ""
echo "Adding MySQL specified user password"
echo ""
mysqladmin --port=%mysql_port% --user=%mysql_username% password %mysql_password%
mysql --port=%mysql_port% --user=%mysql_username% --password=%mysql_password% -e "show databases" > NUL
IF ERRORLEVEL 1 goto notstarted
echo ""
echo "Started MySQL on port specified by the user"
echo ""
goto checkdatabase

:checkdatabase
echo ""
echo "check to see if %database_name% database already exists"
echo ""
mysql --port=%mysql_port% --user=%mysql_username% --password=%mysql_password% -e "show databases like'%database_name%'" | "%WINDIR%\system32\find.exe" "%database_name%" > NUL
IF ERRORLEVEL 1 goto dbnotexists
echo ""
ECHO  "%database_name% database exists"
echo ""
goto end

:dbnotexists
echo ""
ECHO "%database_name% database does not exist"
echo ""
echo %cd%
echo ""
echo "Proceeding to create database %database_name% and populate the same"
echo ""
mysql --user=%mysql_username% --password=%mysql_password% --port=%mysql_port% -e "create database if not exists %database_name% default character set utf8 default collate utf8_general_ci"
echo ""
echo "%database_name% database created"
echo ""
goto end

:notstarted
echo ""
echo ""
ECHO "Unable to start the MySQL server at port %mysql_port%. Check if the port iwinss free"
echo ""
echo ""
set /p pt=Press Any Key to Continue...
goto end

:stopservice
echo ""
echo ""
echo "********* Service not started as port # %apache_port% occupied ******* "
echo ""
echo ""
echo ""
echo "********* I am sorry. I am not able to start the product as the apache port that you have specified:  port # %apache_port% seems to be occupied ******* "
echo ""
echo ""
echo "You could give me a different port number if you wish by doing the following ...."
echo ""
echo ""
echo "********* Open the apache/conf/httpd.conf file, search for "Listen" and change the port number ******* "
echo ""
echo ""
echo ""
echo "********* Change the apache port in startvTiger.bat and stopvTiger.bat too and then access the product from the browser in the following manner http://localhost:apacheport******* "
echo ""
echo ""
echo "Thank You"
echo ""
echo ""

set /p pt=Press Any Key to Continue...
goto end

:end
cd /d %VTIGER_HOME%\bin
start http://localhost:8888