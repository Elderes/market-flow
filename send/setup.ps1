# Build the project, skipping tests
mvn install -DskipTests

# Enter the target folder of the built project
cd (Get-ChildItem -Recurse -Directory -Filter target | Select-Object -ExpandProperty FullName)

# Find any .jar file and run it
$jarFile = Get-ChildItem -Filter *.jar | Select-Object -ExpandProperty Name
if ($jarFile) {
    java -jar $jarFile
} else {
    Write-Host "No .jar file found in the target folder."
}
