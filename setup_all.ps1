# Define MySQL credentials and database names with "db_" prepended to each
$mysqlUser = "root"  # Replace with your MySQL username
$mysqlPassword = "123"  # Replace with your MySQL password
$databaseNames = @("db_order", "db_stock", "db_payment", "db_status", "db_send")

# Path to the SQL file containing the insert statements
$sqlFilePath = ".\seed.sql"  # Adjust the path to your actual file

# Loop through the database names and create each one if it does not exist
foreach ($dbName in $databaseNames) {
    $createDatabaseCommand = "CREATE DATABASE IF NOT EXISTS $dbName;"

    # Command to execute
    $command = "mysql -u $mysqlUser -p$mysqlPassword -e `"$createDatabaseCommand`""

    # Execute the command
    Invoke-Expression $command

    Write-Host "Database '$dbName' created successfully."
}

# Read the SQL file contents
$sqlScript = Get-Content $sqlFilePath -Raw

# Execute the SQL file content for db_stock
$insertCommand = "mysql -u $mysqlUser -p$mysqlPassword db_stock -e `"$sqlScript`""

# Execute the insert command
Invoke-Expression $insertCommand

Write-Host "Data inserted into db_stock.tb_product successfully."

# Path to the parent directory containing all project folders
$parentDir = ".\"

# Define the list of sub-projects (folders)
$subProjects = @("stock", "order", "status", "payment", "send")

# Variable to track if all projects succeeded
$allProjectsSucceeded = $true

# Loop through each sub-project folder
foreach ($subProject in $subProjects) {
    # Path to the current project folder
    $projectPath = Join-Path -Path $parentDir -ChildPath $subProject

    Write-Host "Checking folder: $projectPath"
    
    # Check if the project folder exists
    if (Test-Path -Path $projectPath) {
        Write-Host "Building project: $subProject"
        
        # Navigate to the project folder (temporarily)
        Push-Location -Path $projectPath

        try {
            # Run Maven build
            mvn clean install -DskipTests

            # Check if Maven build succeeded
            if ($LASTEXITCODE -ne 0) {
                throw "Maven build failed for project: $subProject"
            }

            # Path to the target folder (relative to the current directory)
            $targetFolder = ".\target"
            Write-Host "Checking target folder: $targetFolder"

            # Check if target folder exists
            if (Test-Path -Path $targetFolder) {
                # Get the .jar file in the target folder
                $jarFile = Get-ChildItem -Path $targetFolder -Filter "*.jar" | Select-Object -First 1

                if ($jarFile) {
                    Write-Host "Running JAR file: $($jarFile.FullName) in a new terminal"
                    
                    # Run the JAR file in a new PowerShell terminal
                    $javaCommand = "java -jar `"$($jarFile.FullName)`""
                    Start-Process -FilePath "powershell" -ArgumentList "-NoExit", "-Command", $javaCommand
                } else {
                    Write-Host "No JAR file found in $targetFolder"
                    $allProjectsSucceeded = $false
                }
            } else {
                Write-Host "Target folder not found in $projectPath"
                $allProjectsSucceeded = $false
            }
        } catch {
            Write-Host "Error: $_"
            $allProjectsSucceeded = $false
        } finally {
            # Return to the parent directory
            Pop-Location
        }
    } else {
        Write-Host "Project folder not found: $projectPath"
        $allProjectsSucceeded = $false
    }
}

# Display final message based on success
if ($allProjectsSucceeded) {
    Write-Host "All projects built and executed successfully!"
} else {
    Write-Host "Some projects failed to build or execute."
}