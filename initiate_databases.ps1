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