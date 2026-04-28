import { ref, reactive, computed, inject } from 'vue'
import { api } from '../services/api.js';

export function useClassData() {
  // Inject global state
  const selectedClass = inject('selectedClass')
  const selectClass = inject('selectClass')

  // Reactive data arrays
  const classesData = ref([])
  const studentsData = ref([])
  const documentsData = ref([])

  // Statistics data
  const statisticsData = reactive({
    subjects: ['math', 'english', 'science'],
    classAverage: [8.2, 7.5, 8.8],
    reachedNorm: ['yes', 'no', 'yes'],
    passingPerc: ['85%', '72%', '91%']
  })

  // Computed properties for filtered data
  const filteredStudents = computed(() => {
    if (!selectedClass.value) return []
    return studentsData.value.filter(student => student.classId === selectedClass.value.id)
  })

  const filteredDocuments = computed(() => {
    if (!selectedClass.value) return []
    return documentsData.value.filter(document => document.classId === selectedClass.value.id)
  })

  const filteredStatistics = computed(() => {
    if (!selectedClass.value) return { subjects: [], classAverage: [], reachedNorm: [], passingPerc: [] }
    return statisticsData
  })

  // CRUD operations
  const addClass = async (classData) => {
    try {
      // Call the API to add the class to the database
      const result = await api.addClass(
        classData.name,
        classData.teacherFirstName,
        classData.teacherLastName
      );
      
      // Create a new class object with the returned ID
      const newClass = {
        id: result.id,
        name: classData.name,
        teacher: `${classData.teacherFirstName} ${classData.teacherLastName}`
      };
      
      // Add to the local state
      classesData.value.push(newClass);
      return newClass;
    } catch (error) {
      console.error('Error adding class to database:', error);
      alert(`Failed to add class: ${error.message}`);
      return null;
    }
  }

  const addStudent = async (studentName) => {
    if (!selectedClass.value) return null;
    
    // Split the student name into first and last name
    // Assuming format is "FirstName LastName"
    const nameParts = studentName.trim().split(' ');
    let firstName = nameParts[0];
    let lastName = nameParts.length > 1 ? nameParts.slice(1).join(' ') : '';
    
    try {
      // Call the API to add the student to the database
      const result = await api.addStudent(
        firstName,
        lastName,
        selectedClass.value.id
      );
      
      // Create a new student object with the returned ID
      const newStudent = {
        id: result.id,
        name: studentName.trim(),
        classId: selectedClass.value.id
      };
      
      // Add to the local state
      studentsData.value.push(newStudent);
      return newStudent;
    } catch (error) {
      console.error('Error adding student to database:', error);
      alert(`Failed to add student: ${error.message}`);
      return null;
    }
  }

  const addDocument = (documentData) => {
    if (!selectedClass.value) return null
    const newDocument = {
      id: Date.now(),
      title: documentData.title,
      lastEdited: new Date().toLocaleDateString('en-GB'),
      editor: documentData.editor,
      classId: selectedClass.value.id
    }
    documentsData.value.push(newDocument)
    return newDocument
  }

  const handleClassSelect = (classItem) => {
    selectClass(classItem)
  }

  return {
    // Data
    classesData,
    studentsData,
    documentsData,
    statisticsData,
    selectedClass,
    
    // Computed
    filteredStudents,
    filteredDocuments,
    filteredStatistics,
    
    // Methods
    addClass,
    addStudent,
    addDocument,
    handleClassSelect
  }
}
