export const formatDate = (date) => {
    try {
        let dateString = date.toISOString();
        dateString = dateString.slice(0, dateString.length - 1) + "+00:00";
        return dateString;
    } catch (error) {
        console.error('Error formatting date:', error);
        return 'Invalid Date';
    }
}
