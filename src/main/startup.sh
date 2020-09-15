#!/bin/bash
echo " "
echo "Variables for the container"
echo " "
echo "DT_OPTIONS=${DT_OPTARGS}"
echo "JAVA_TOOL_OPTIONS=${JAVA_TOOL_OPTIONS}"
echo "LD_LIBRARY_PATH=${LD_LIBRARY_PATH}"
echo "APPNAME=${APPNAME}"
echo "RUNTIME_ENVIRONMENT=${RUNTIME_ENVIRONMENT}"
echo "Possible values for RUNTIME_ENVIRONMENT: dev test prod"
echo "JAVA_HOME=${JAVA_HOME}"
echo " "

# Verify APPNAME environment variable
if [ -z ${APPNAME} ]; then
    echo "ERROR: APPNAME var not set. Cannot start application."
    exit ;
fi

# Check runtime environment for the service
USAGE="usage: ${0} [dev|test|prod]"
if [ -z ${RUNTIME_ENVIRONMENT} ]; then
    echo "RUNTIME_ENVIRONMENT not found, Setting from user input";
    RUNTIME_ENVIRONMENT=${1};
    if [ -z ${RUNTIME_ENVIRONMENT} ]; then
        echo "RUNTIME_ENVIRONMENT variable was not set. Cannot start application.";
        echo "${USAGE}";
        exit 1;
    fi
fi
if [[ ! "dev test prod" =~ (^| )$RUNTIME_ENVIRONMENT($| ) ]]; then
    echo "Invalid RUNTIME_ENVIRONMENT value, RUNTIME_ENVIRONMENT. Cannot start application.";
    echo "${USAGE}";
    exit 1;
fi

#
# Add certs for the environment to keystore
#
echo "Adding certs for ${RUNTIME_ENVIRONMENT} to java keystore"
keytool -import -storepass changeit -noprompt -trustcacerts -alias ${RUNTIME_ENVIRONMENT}-root -file /opt/${APPNAME}/certs/${RUNTIME_ENVIRONMENT}/${RUNTIME_ENVIRONMENT}-root-ca.cer -keystore ${JAVA_HOME}/lib/security/cacerts

export LOGGING_FILE=/var/log/${APPNAME}/${APPNAME}.log
export PROG_JAR=/opt/${APPNAME}/${APPNAME}.jar

JAVA_OPTS="${JAVA_OPTS} -Dlogging.file=${LOGGING_FILE} -Dspring.profiles.active=${RUNTIME_ENVIRONMENT},auth-svc,registration-svc,profile"
echo "JAVA OPTIONS: ${JAVA_OPTS}"

echo " "
echo "======================================"
echo "Starting application [${APPNAME}]"
echo "======================================"
java \
    ${JAVA_OPTS} \
    -jar ${PROG_JAR}
