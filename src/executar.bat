@echo off
echo ========================================
echo   EXECUTANDO PROJETO TANUKI_PUCPR
echo ========================================
echo.

if not exist out (
    echo ERRO: Projeto nao compilado!
    echo Execute primeiro o arquivo compilar.bat
    pause
    exit /b 1
)

java -cp out main.Main

echo.
echo ========================================
echo   EXECUCAO FINALIZADA
echo ========================================
echo.
echo Arquivos gerados em src/:
if exist financiamentos.txt (
    echo   [OK] financiamentos.txt
) else (
    echo   [--] financiamentos.txt
)
if exist financiamentos.dat (
    echo   [OK] financiamentos.dat
) else (
    echo   [--] financiamentos.dat
)
echo.
pause

