package employee.impl;

import employee.exceptions.EmployeeAlreadyExistsException;
import employee.model.Employee;
import employee.model.EmployeeCategory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.logging.Logger;

class JsonHelper {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static final String FILE_PATH = "employee.json";

    void addEmployee(Employee employee) throws
            EmployeeAlreadyExistsException {
        alterJsonFile(employee, false);
        log.info("Employee " + employee.getEmployeeName() + " added succesfully");
    }

    void removeEmployee(Employee employee) {
        try {
            alterJsonFile(employee, true);
        } catch (EmployeeAlreadyExistsException e) {
            e.printStackTrace();
        }
        log.info("Employee " + employee.getEmployeeName() + " removed succesfully");
    }

    Employee getEmployee(String employeeName) {
        JSONArray jsonArray = getJsonFromFile();
        ArrayList<Employee> employees = getEmployeesFromJson(jsonArray);
        return employees.stream()
                .filter(a -> a.getEmployeeName().equalsIgnoreCase(employeeName))
                .findFirst()
                .get();
    }

    ArrayList<Employee> getAllEmployees() {
        JSONArray jsonArray = getJsonFromFile();
        return getEmployeesFromJson(jsonArray);
    }

    private void alterJsonFile(Employee employee, boolean removeEmployee) throws
            EmployeeAlreadyExistsException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Employee> employeeList = new ArrayList<>();
        if (!isFileEmpty()) {
            employeeList = addExistingDataToList(employeeList, mapper);
        }
        if (removeEmployee) {
            removeEmployeeFromList(employeeList, employee);
        } else {
            verifyNewEmployeeNotExist(employee.getEmployeeName());
            employeeList.add(employee);
        }
        writeDataToFile(employeeList, mapper);
    }

    private void removeEmployeeFromList(ArrayList<Employee> employeeList, Employee employeeToRemove) {
        Object[] o = employeeList.toArray();
        for (int i =0; i< o.length; i++) {
            String employeeName = ((LinkedHashMap) o[i]).get("employeeName").toString();
            if (employeeName.equalsIgnoreCase(employeeToRemove.getEmployeeName())) {
                employeeList.remove(i);
            }
        }
    }

    private JSONArray getJsonFromFile() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(FILE_PATH));
        } catch (Exception e) {
            return null;
        }
        return jsonArray;
    }

    private void verifyNewEmployeeNotExist(String employeeName) throws
            EmployeeAlreadyExistsException {
        JSONArray jsonArray = getJsonFromFile();
        if (jsonArray != null) {
            ArrayList<Employee> employees = getEmployeesFromJson(jsonArray);
            Optional<Employee> employee = employees.stream().filter(a -> a.getEmployeeName().equalsIgnoreCase(employeeName)).findAny();
            if (employee.isPresent()) {
                throw new EmployeeAlreadyExistsException(employeeName);
            }
        }
    }

    private ArrayList<Employee> getEmployeesFromJson(JSONArray jsonArray) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            employees.add(new Employee((String) jsonObject.get("employeeName"),
                    (Math.toIntExact((Long) jsonObject.get("employeeSalary"))),
                    EmployeeCategory.toEnum((String) jsonObject.get("employeeCategory"))));
        }
        return employees;
    }

    private ArrayList addExistingDataToList(ArrayList list, ObjectMapper mapper) {
        String existingFile = readFile();
        try {
            list = mapper.readValue(existingFile,ArrayList.class);
        } catch (Exception e) {
            e.printStackTrace();
        } return list;
    }

    private void writeDataToFile(ArrayList list, ObjectMapper mapper) {
        PrintWriter pw = null;
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(new File(FILE_PATH));
            pw = new PrintWriter(file);
            pw.print(mapper.writeValueAsString(list));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isFileEmpty() {
        if (readFile() == null) {
            return true;
        } else
            return false;
    }

    private String readFile() {
        FileReader fileInput = null;
        BufferedReader br = null;
        String existingData = null;
        try {
            fileInput = new FileReader(FILE_PATH);
            br = new BufferedReader(fileInput);
            existingData = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return existingData;
    }
}