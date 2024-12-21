package com.prekdu;

import java.util.*;

/*
 * Design a Library Management System that handles different types of library resources
(books, digital content, periodicals). The system should demonstrate(Use enums, classes, functions etc):

1. Create an abstract class LibraryResource that contains:
   - Protected fields for resourceId, title, and availabilityStatus
   - Abstract method calculateLateFee(int daysLate)
   - Abstract method getMaxLoanPeriod()

2. Implement different types of resources:
   - Book: Has additional fields for author and ISBN
   - DigitalContent: Has fields for fileSize and format (PDF, EPUB)
   - Periodical: Has fields for issueNumber and frequency (WEEKLY, MONTHLY)

3. Create an interface Renewable with method:
   - boolean renewLoan(LibraryMember member)

4. Create an interface Reservable with methods:
   - void reserve(LibraryMember member)
   - void cancelReservation(LibraryMember member)

5. Implement a LibraryMember class that:
   - Maintains a list of borrowed resources
   - Has a membership type (STANDARD, PREMIUM)
   - Has methods to borrow and return resources

6. Implement proper exception handling for:
   - ResourceNotAvailableException
   - MaximumLoanExceededException
 - InvalidMembershipException

Requirements:
- Books and Periodicals should be both Renewable and Reservable
- DigitalContent should only be Renewable
- Different resource types should have different late fees and loan periods
- Premium members should have higher loan limits and lower late fees and loan periods
- Implement proper validation and business logic

 */

// Exception Handling
class ResourceNotAvailableException extends Exception {
  public ResourceNotAvailableException(String message) {
    super(message);
  }
}

class MaximumLoanExceededException extends Exception {
  public MaximumLoanExceededException(String message) {
    super(message);
  }
}

class InvalidMembershipException extends Exception {
  public InvalidMembershipException(String message) {
    super(message);
  }
}

// defining the Abstract class
abstract class LibraryResource {
  protected String resourceId;
  protected String title;
  protected boolean availabilityStatus;

  public LibraryResource(String resourceId, String title) {
    this.resourceId = resourceId;
    this.title = title;
    this.availabilityStatus = true;
  }

  public abstract double calculateLateFee(int daysLate);

  public abstract int getMaxLoanPeriod();

  public boolean isAvailable() {
    return availabilityStatus;
  }

  public void setAvailability(boolean availability) {
    this.availabilityStatus = availability;
  }

  public String getStatus() {
    return availabilityStatus ? "AVAILABLE" : "BORROWED";
  }
}

interface Renewable {
  boolean renewLoan(LibraryMember member);
}

interface Reservable {
  void reserve(LibraryMember member);

  void cancelReservation(LibraryMember member);
}

class Book extends LibraryResource implements Renewable, Reservable {
  private String author;
  private String ISBN;
  private LibraryMember reservedBy;

  public Book(String resourseId, String title, String author, String ISBN) {
    super(resourseId, title);
    this.author = author;
    this.ISBN = ISBN;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.5;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 21;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return member.canRenew(this);
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null) {
      throw new IllegalStateException("Resource is already reserved.");
    }
    reservedBy = member;
    System.out.println("Book reserved by " + member.getName());
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy == member) {
      reservedBy = null;
      System.out.println("Reservation canceled by " + member.getName());
    } else {
      throw new IllegalStateException("Resource not reserved by thid member.");
    }
  }
}

class DigitalContent extends LibraryResource implements Renewable {
  private double fileSize;
  private ContentFormat format;

  public DigitalContent(String resourceId, String title, double fileSize, ContentFormat format) {
    super(resourceId, title);
    this.fileSize = fileSize;
    this.format = format;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.25;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 14;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return member.canRenew(this);
  }
}

class Periodical extends LibraryResource implements Renewable, Reservable {
  private int issueNumber;
  private String frequency;
  private LibraryMember reservedBy;

  public Periodical(String resourceId, String title, int issueNumber, String frequency) {
    super(resourceId, title);
    this.issueNumber = issueNumber;
    this.frequency = frequency;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.1;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return member.canRenew(this);
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null) {
      throw new IllegalStateException("Resources is already reserved.");
    }
    reservedBy = member;
    System.out.println("Periodical reserved by " + member.getName());
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy == member) {
      reservedBy = null;
      System.out.println("Reservation canceled by " + member.getName());
    } else {
      throw new IllegalStateException("Resource not reserved by this member.");
    }
  }
}

enum MembershipType {
  STANDARD,
  PREMIUM
}

enum ContentFormat {
  PDF,
  EPUB
}

enum ResourceStatus {
  AVAILABLE,
  BORROWED
}

class LibraryMember {
  private String name;
  private MembershipType membershipType;
  private List<LibraryResource> borrowedResources;
  private static final int STANDARD_LIMIT = 5;
  private static final int PREMIUM_LIMIT = 10;

  public LibraryMember(String name, MembershipType membershipType) {
    this.name = name;
    this.membershipType = membershipType;
    this.borrowedResources = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void borrowResource(LibraryResource resource)
      throws ResourceNotAvailableException, MaximumLoanExceededException {
    try {
      if (!resource.isAvailable()) {
        throw new ResourceNotAvailableException("Resource is not available.");
      }

      if (borrowedResources.size()
          >= (membershipType == MembershipType.STANDARD ? STANDARD_LIMIT : PREMIUM_LIMIT)) {
        throw new MaximumLoanExceededException("Loan limit exceeded.");
      }

      borrowedResources.add(resource);
      resource.setAvailability(false);
      System.out.println(name + " borrowed " + resource.title);
    } catch (ResourceNotAvailableException | MaximumLoanExceededException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public void returnResource(LibraryResource resource) {
    borrowedResources.remove(resource);
    resource.setAvailability(true);
    System.out.println(name + "returned " + resource.title);
  }

  public List<LibraryResource> getBorrowedResources() {
    return borrowedResources;
  }

  public boolean canRenew(LibraryResource resource) {
    return borrowedResources.contains(resource);
  }
}
