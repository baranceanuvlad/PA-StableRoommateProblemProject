Stable Roommate Problem (Baranceanu Vlad-Andrei si Apetrii Radu-Andrei)

Rezumat: Luand in considerare o multime de studenti, sa se genereze, daca se poate, o repartizare a camerelor din camin.

Baza de date
	Aplicatia este conectata la baza de date locala unde sunt retinute detaliile despre studenti si preferintele lor, iar id-ul fiecarui student este creat in mod automat prin intermediul unui sequence.
	Conexiunea la baza de date se realizeaza prin intermediul unui persistance unit.
	Totodata, in cod sunt prezente si query-uri care adauga, modifica si elimina studenti si preferenti din baza de date.

Algoritmul lui Irving
	Problema pe care si-o propune aceasta aplicatie sa o rezolve este solutionata prin intermediul algoritmul lui Irving care este implementat de catre noi in cod.

Multi-threading
	Aplicatia permite functionalitatea de multi-threading, prin existenta unui server si prin crearea unui thread separat pentru fiecare client care se conecteaza.

User Interface
	Cu ajutorul Java Swing, am creat o interfata grafica pentru a-i fi mai usor unui utilizator sa acceseze aplicatia.
	Sunt prezente butoane precum:
	- Add Student (unde ulterior se vor cere detalii despre studentul care urmeaza a fi adaugat)
	- Modify Student (unde se modifica lista de preferinte a unui student)
	- Delete Student (unde se sterge studentul ales din baza de date)
	- Show Preferences (pentru a vizualiza lista de preferinte a unui student)
	- Generate Solution (unde se incearca repartizarea in camere)
	- Exit (buton pentru a inchide thread-ul curent)
	- Exit Server (pentru a se inchide serverul si toate thread-urile)

Aplicatie este structurata sa respecte normele SOLID.