
@echo off
cd ..
set mysql_dir=D:\Vtiger\vtigercrm-5.4.0\mysql
set mysql_username=root
set mysql_password=root
set mysql_port=33307
set mysql_bundled=true
set apache_dir=D:\Vtiger\vtigercrm-5.4.0\apache
set apache_bin=D:\Vtiger\vtigercrm-5.4.0\apache\bin
set apache_conf=D:\Vtiger\vtigercrm-5.4.0\apache\conf
set apache_port=8888
set apache_bundled=true
set apache_service=true

set VTIGER_HOME=%cd%

if %apache_bundled% == true goto StopApacheCheck
goto StopMySQL

:StopApacheCheck
if %apache_service% == true goto StopApacheService
cd /d %apache_dir%
rem shut down apache
echo ""
echo "stopping vtigercrm APACHE"
echo ""
bin\ShutdownApache logs\httpd.pid
goto StopMySQL

:StopApacheService
cd /d %apache_dir%
rem shut down apache
echo ""
echo "stopping vtigercrmApache540 APACHE service"
echo ""
bin\apache -n vtigercrmApache540 -k stop
echo ""
echo "uninstalling vtigercrmApache540 APACHE service"
echo ""
bin\apache -k uninstall -n vtigercrmApache540
rem .\bin\ShutdownApache.exe logs\httpd.pid
goto StopMySQL

:StopMySQL
if %mysql_bundled% == true (
	rem cd /d %VTIGER_HOME%\mysql\bin
	rem  shutdown mysql
	cd /d %mysql_dir%\bin
	mysqladmin --port=%mysql_port% --user=%mysql_username% --password=%mysql_password% shutdown
	echo ""
	echo "vtiger CRM  MySQL Sever is shut down"
	echo ""
	echo ""
	echo "uninstalling vtigercrmMysql540 MYSQL service"
	echo ""
mysqladmin shutdown
mysqld --remove vtigercrmMysql540
	cd /d %VTIGER_HOME%\bin
)