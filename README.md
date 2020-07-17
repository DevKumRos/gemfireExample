# gemfireExample

Steps:
Install Gemfire Server First.
Set Gemfire server Classpath in system environment variable.
User Variable
GF_JAVA=%JAVA_HOME%\bin\java.exe
update Path variable.

Create working folder 
# Start GmeFire command prompt with below code.
set 
GemFire-Folder> gfsh 

# Start Locator this mandatory
gsfh>start locator --name=helloLocator

# Option 1:
# Start Server basically configuration 
start server  --name=helloserver

# Option 2:
# Start server with xml configuration & cache.xml file is in resource folder
start server  --name=ActivityTrackerServer --cache-xml-file=cache.xml

# To see what all running
gfsh>list members

# start pulse
This command to check data in browser 
select  e.key, e.firstName, e.lastName  from /Hello e

# Few Commands to check behavior

# Create a region replicated if Option 1
gfsh>create region --name=Hello --type=REPLICATE

# Use the gfsh command line to view a list of the regions in the cluster.
gfsh>list regions

# To view specifics about a region, type the following:
gfsh>describe region --name=Hello

# Put commands to add some data to the region only for non custom object value:
gfsh>put --region=Hello --key="1" --value="one"

# Remove particular key 
gfsh>remove --region=/Hello --key=('hello': 'hello')

# Remove All keys in region
gfsh> remove --region=/Hello --all=true

#Delete Region 
 gfsh>destroy region --name=Hello

# Query for getting data
 query --query='select * from /Hello'

# Query for getting data in pulse

 select e.key from /Hello.keySet()  e
 
 select e.key from /Hello.entrySet()  e

select  e.key, e.firstName, e.lastName  from /Hello e

# Shut down any cache servers. 
gfsh>stop server --name=helloserver

# Shut down any locators
gfsh>stop locator --name=helloLocator

# Shutdown gfsh
gfsh>exit

 # Stop all memebers
 gfsh>shutdown --inculde-locators=true
 
 
# How to run application
Step1: Start gfsh, Locator, server & create Region.
Step2: Download project Run HelloWorldApplication 
Step3: http://localhost:9090/hello/{key}
Step4: try to access same key it will return within 3ms & 1st time it will take more then 3sec.




