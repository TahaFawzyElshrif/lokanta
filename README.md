# lokanta
# basic version
**I'm taha elshrif**
**the only who  worked at this project !**
**I'm not a programmer I'm only at jounior 1 so you may found some issus may be big  '**
_What is this program_
this is ==Windows== application ,written in java-R for originizing the work of many restaurants

_Capabilities_
make meals,offers-->for manager(Chielf)
ordering meal,offer-->for customer
analyzing best-selled,assosiation rules(second-third only)-->managers
**Files service I gonna to add it**
**I tried to orginaze code as possible but you may found some difficult or mistakes**



_Borders of work_
1)manager has to deal with only one restaurant with many branches at the same device

_instillation of exe file_
run instill program I atteched then choose a path to install is C:\Lokanta if you don't do it photos won't work


_how it work_
in the first time after instill you add a restaurant with password(don't forget it) (if you tried to add next time some thing will go wrong, in Fact i did it with my intent not an issue)
after you add it ,it will create folders at C:\Lokanta with restaurant saved on it .dat
then add branch and code(don't forget it) after it the branch will be added on C .dat
**after adding meal ,or offers it will only be added to branch object not restaurant object**

_about program structure_
**three packages** :
interfaces:gates(abstract class),serviceINT(service interface ,,,abstract class),serviceINTCUST(service interface for customers,,,abstract class)
objects:main objects (that actually things not relate to javafx file)
main:program(javafx files)
**about structure**:
-all javafx classes that require login data inherit gates 
-all javafx classes that relate to managers inherit serviceINT
-all javafx classes that relate to managers inherit serviceINTCUST
------------
-service is base class for offer,meal
**how I saved Files**
I used very basic tools to it 
-lanch4j to convert jar to exe
-IExpress to make instillation file




_issus I had_
1)I don't have a database for saving restaurant and use this program for wide use
2)customer login with email ,and the program don't make account for him
3)analyzing was made with R ,may require installing R language at device ,may also work at code only not at exe file
5)after ordering offer ,same problems will happen
6)I tried to save a anylsics plot as pdf/image but something i don't know went wrong
7)at Offers button when showing offers if you finished all previous and click next then you can't click previous
8)some issus may go and not attempt user to  happened 
9)I can't resolve the problem of fitness for all screens
10)if the manager forgetten password ,he wouldn't be able to return it
11)structure of data files not very organized


