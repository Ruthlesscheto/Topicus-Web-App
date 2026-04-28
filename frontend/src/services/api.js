// API service for connecting to backend endpoints

// Base URL for API requests
const API_URL = '/api';

export const api= {
  getClasses,
  getQuestionsInDocument,
  getStudentForQuestion,
  getGradesForStudent,
  getGradeBySubject,
  getNotificationsForEditor,
  getTeacherInfo,
  addStudent,
  addClass,
}

export async function getClasses() {
  try {
    const response = await fetch(`${API_URL}/classes`);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching classes:', error);
    throw error;
  }
}

// Questions API
export async function getQuestionsInDocument(className) {
  try {
    const response = await fetch(`${API_URL}/questions/document/${encodeURIComponent(className)}`);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching questions:', error);
    throw error;
  }
}

export async function getStudentForQuestion(question) {
  try {
    const response = await fetch(`${API_URL}/questions/student/${encodeURIComponent(question)}`);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching student:', error);
    throw error;
  }
}

// Grades API
export async function getGradesForStudent(firstName, lastName) {
  try {
    const response = await fetch(
      `${API_URL}/grades/student/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}`
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching grades:', error);
    throw error;
  }
}

export async function getGradeBySubject(firstName, lastName, subject) {
  try {
    const response = await fetch(
      `${API_URL}/grades/student/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}/subject/${encodeURIComponent(subject)}`
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching grade:', error);
    throw error;
  }
}

// Notifications API
export async function getNotificationsForEditor(firstName, lastName) {
  try {
    const response = await fetch(
      `${API_URL}/notifications/editor/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}`
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching notifications:', error);
    throw error;
  }
}

// Teacher API
export async function getTeacherInfo() {
  try {
    const response = await fetch(`${API_URL}/teacher`);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching teacher info:', error);
    throw error;
  }
}

// Student API
export async function addStudent(firstName, lastName, classId) {
  try {
    const response = await fetch(`${API_URL}/students`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        firstName,
        lastName,
        classId: classId.toString()
      }),
    });
    
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! Status: ${response.status}, Message: ${errorText}`);
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error adding student:', error);
    throw error;
  }
}

// Class API
export async function addClass(className, teacherFirstName, teacherLastName) {
  try {
    const response = await fetch(`${API_URL}/classes`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: className,
        teacherFirstName,
        teacherLastName
      }),
    });
    
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! Status: ${response.status}, Message: ${errorText}`);
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error adding class:', error);
    throw error;
  }
}



