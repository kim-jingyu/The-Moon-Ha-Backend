#!/bin/bash

PROJECT_ROOT="/home/ubuntu/backend”
TARGET_DIR="$PROJECT_ROOT/target"
WEBAPPS_DIR="/opt/tomcat/webapps"

# 1. Tomcat 서버 중지
echo "Stopping Tomcat..."
sudo systemctl stop tomcat

# 2. 프로젝트 디렉터리 이동 및 빌드
echo "Navigating to project directory..."
cd $PROJECT_ROOT || { echo "Directory not found!"; exit 1; }

echo "Removing target directory..."
rm -rf "$TARGET_DIR"

echo "Building the project..."
mvn clean package

# 3. WAR 파일 찾기 (빌드된 최신 WAR 파일을 찾음)
WAR_FILE=$(ls "$TARGET_DIR"/*.war)
WAR_NAME=$(basename "$WAR_FILE")

# 4. 웹앱 디렉토리로 이동
echo "Navigating to Tomcat webapps directory..."
cd "$WEBAPPS_DIR" || { echo "Webapps directory not found!"; exit 1; }

# 5. 기존 파일 삭제
echo "Removing old WAR and exploded directory..."
sudo rm -rf "$WAR_NAME"
sudo rm -rf "${WAR_NAME%.war}"

# 6. 새 WAR 파일 복사
echo "Copying new WAR file..."
sudo cp "$WAR_FILE" "$WEBAPPS_DIR"

# 7. 권한 설정
echo "Setting ownership to Tomcat..."
sudo chown tomcat:tomcat "$WAR_NAME"

# 8. Tomcat 서버 시작
echo "Starting Tomcat..."
sudo systemctl start tomcat

echo "Deployment finished!"