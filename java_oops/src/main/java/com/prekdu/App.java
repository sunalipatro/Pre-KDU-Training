package com.prekdu;

import java.util.ArrayList;
import java.util.List;

/** Represents the status of a library resource. */
enum ResourceStatus {
  /** Resource is available for borrowing. */
  AVAILABLE,
  /** Resource is currently borrowed. */
  BORROWED,
  /** Resource is reserved. */
  RESERVED
}

/** Represents the type of library membership. */
enum MembershipType {
  /** Standard membership with basic privileges. */
  STANDARD,
  /** Premium membership with enhanced privileges. */
  PREMIUM
}

/** Represents digital content format types. */
enum ContentFormat {
  /** Portable Document Format. */
  PDF,
  /** Electronic Publication Format. */
  EPUB
}

/** Represents publication frequency for periodicals. */
enum Frequency {
  /** Weekly publication. */
  WEEKLY,
  /** Monthly publication. */
  MONTHLY
}

/** Represents constants used across the library management system. */
public final class App {
  private App() {
    throw new UnsupportedOperationException("Cannot be instantiated");
  }

  /** Book Late Fee. */
  public static final double BOOK_LATE_FEE = 0.5;

  /** Book Loan Period. */
  public static final int BOOK_LOAN_PERIOD = 14;

  /** Digital Late Fee. */
  public static final double DIGITAL_LATE_FEE = 0.25;

  /** Digital Loan Period. */
  public static final int DIGITAL_LOAN_PERIOD = 7;

  /** Periodical Late Fee. */
  public static final double PERIODICAL_LATE_FEE = 0.3;

  /** Periodical Loan Period. */
  public static final int PERIODICAL_LOAN_PERIOD = 7;

  /** Premium Borrow Limit. */
  public static final int PREMIUM_BORROW_LIMIT = 10;

  /** Standard Borrow Limit. */
  public static final int STANDARD_BORROW_LIMIT = 5;
}

abstract class LibraryResource {
  /** Resource id. */
  private String resourceId;

  /** title of resource. */
  private String title;

  /** availabe status. */
  private ResourceStatus availabilityStatus;

  LibraryResource(final String r, final String t) {
    this.resourceId = r;
    this.title = t;
    this.availabilityStatus = ResourceStatus.AVAILABLE;
  }

  public abstract double calculateLateFee(int daysLate);

  public abstract int getMaxLoanPeriod();

  public ResourceStatus getStatus() {
    return availabilityStatus;
  }

  public void setStatus(final ResourceStatus status) {
    this.availabilityStatus = status;
  }

  public String getResourceId() {
    return this.resourceId;
  }

  public String getTitle() {
    return this.title;
  }
}

interface Renewable {
  boolean renewLoan(LibraryMember member);
}

interface Reservable {
  void reserve(LibraryMember member);

  void cancelReservation(LibraryMember member);
}

/** Represents a book in the library. */
class Book extends LibraryResource implements Renewable, Reservable {
  /** Book author. */
  private String author;

  /** Book ISBN. */
  private String isbn;

  /** Member who reserved the book. */
  private LibraryMember reservedBy;

  Book(final String r, final String t, final String a, final String i) {
    super(r, t);
    this.author = a;
    this.isbn = i;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getIsbn() {
    return this.isbn;
  }

  @Override
  public double calculateLateFee(final int daysLate) {
    return daysLate * App.BOOK_LATE_FEE;
  }

  @Override
  public int getMaxLoanPeriod() {
    return App.BOOK_LOAN_PERIOD;
  }

  @Override
  public boolean renewLoan(final LibraryMember member) {
    return getStatus() == ResourceStatus.BORROWED && reservedBy == null;
  }

  @Override
  public void reserve(final LibraryMember member) {
    if (getStatus() == ResourceStatus.AVAILABLE || reservedBy != null) {
      throw new IllegalStateException("Resource cannot be reserved.");
    }
    reservedBy = member;
    setStatus(ResourceStatus.RESERVED);
  }

  @Override
  public void cancelReservation(final LibraryMember member) {
    if (reservedBy.equals(member)) {
      reservedBy = null;
      setStatus(ResourceStatus.AVAILABLE);
    }
  }
}

/** Represents digital content in the library. */
class DigitalContent extends LibraryResource implements Renewable {
  /** Content File Size. */
  private double fileSize;

  /** Content format. */
  private ContentFormat format;

  DigitalContent(
      final String resourceId,
      final String title,
      final double fileSiz,
      final ContentFormat formt) {
    super(resourceId, title);
    this.fileSize = fileSiz;
    this.format = formt;
  }

  public double getFileSize() {
    return this.fileSize;
  }

  public ContentFormat getFileFormat() {
    return this.format;
  }

  @Override
  public double calculateLateFee(final int daysLate) {
    return daysLate * App.DIGITAL_LATE_FEE;
  }

  @Override
  public int getMaxLoanPeriod() {
    return App.DIGITAL_LOAN_PERIOD;
  }

  @Override
  public boolean renewLoan(final LibraryMember member) {
    return getStatus() == ResourceStatus.BORROWED;
  }
}

/** Represents a periodical in the library. */
class Periodical extends LibraryResource implements Renewable, Reservable {
  /** Periodical issueNumber. */
  private int issueNumber;

  /** frequency. */
  private Frequency frequency;

  /** Member who reserved book. */
  private LibraryMember reservedBy;

  Periodical(final String r, final String t, final int i, final Frequency f) {
    super(r, t);
    this.issueNumber = i;
    this.frequency = f;
  }

  public Frequency getFrequency() {
    return this.frequency;
  }

  public int getIssueNumber() {
    return this.issueNumber;
  }

  @Override
  public double calculateLateFee(final int daysLate) {
    return daysLate * App.PERIODICAL_LATE_FEE;
  }

  @Override
  public int getMaxLoanPeriod() {
    return App.PERIODICAL_LOAN_PERIOD;
  }

  @Override
  public boolean renewLoan(final LibraryMember member) {
    return getStatus() == ResourceStatus.BORROWED && reservedBy == null;
  }

  @Override
  public void reserve(final LibraryMember member) {
    if (getStatus() == ResourceStatus.AVAILABLE || reservedBy != null) {
      throw new IllegalStateException("Resource cannot be reserved.");
    }
    reservedBy = member;
    setStatus(ResourceStatus.RESERVED);
  }

  @Override
  public void cancelReservation(final LibraryMember member) {
    if (reservedBy.equals(member)) {
      reservedBy = null;
      setStatus(ResourceStatus.AVAILABLE);
    }
  }
}

/** Represents a library member. */
class LibraryMember {
  /** Member Id. */
  private String memberId;

  /** Membership Type. */
  private MembershipType membershipType;

  /** Borrowed Books. */
  private List<LibraryResource> borrowedResources;

  LibraryMember(final String memId, final MembershipType memType) {
    this.memberId = memId;
    this.membershipType = memType;
    this.borrowedResources = new ArrayList<>();
  }

  public String getMemberId() {
    return this.memberId;
  }

  public void borrowResource(final LibraryResource resource) {
    int limit =
        membershipType == MembershipType.PREMIUM
            ? App.PREMIUM_BORROW_LIMIT
            : App.STANDARD_BORROW_LIMIT;
    if (borrowedResources.size() >= limit) {
      throw new MaximumLoanExceededException("Borrow limit exceeded.");
    }
    if (resource.getStatus() != ResourceStatus.AVAILABLE) {
      throw new ResourceNotAvailableException("Resource is not available.");
    }
    borrowedResources.add(resource);
    resource.setStatus(ResourceStatus.BORROWED);
  }

  public void returnResource(final LibraryResource resource) {
    borrowedResources.remove(resource);
    resource.setStatus(ResourceStatus.AVAILABLE);
  }

  public List<LibraryResource> getBorrowedResources() {
    return borrowedResources;
  }
}

/** Exception thrown when a resource is not available. */
class ResourceNotAvailableException extends RuntimeException {
  ResourceNotAvailableException(final String message) {
    super(message);
  }
}

/** Exception thrown when the borrow limit is exceeded. */
class MaximumLoanExceededException extends RuntimeException {
  MaximumLoanExceededException(final String message) {
    super(message);
  }
}

/** Exception thrown for invalid membership actions. */
class InvalidMembershipException extends RuntimeException {
  InvalidMembershipException(final String message) {
    super(message);
  }
}
