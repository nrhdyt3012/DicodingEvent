package com.example.dicodingevent.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventEntity> __insertionAdapterOfEventEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventEntity = new EntityInsertionAdapter<EventEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EventEntity` (`id`,`name`,`ownerName`,`beginTime`,`quota`,`registrants`,`description`,`mediaCover`,`link`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EventEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getOwnerName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOwnerName());
        }
        if (value.getBeginTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBeginTime());
        }
        if (value.getQuota() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getQuota());
        }
        if (value.getRegistrants() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRegistrants());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getMediaCover() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMediaCover());
        }
        if (value.getLink() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getLink());
        }
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM EventEntity WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final EventEntity eventEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventEntity.insert(eventEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteById(final int eventId, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, eventId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getEventById(final int eventId,
      final Continuation<? super EventEntity> continuation) {
    final String _sql = "SELECT * FROM EventEntity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EventEntity>() {
      @Override
      public EventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfBeginTime = CursorUtil.getColumnIndexOrThrow(_cursor, "beginTime");
          final int _cursorIndexOfQuota = CursorUtil.getColumnIndexOrThrow(_cursor, "quota");
          final int _cursorIndexOfRegistrants = CursorUtil.getColumnIndexOrThrow(_cursor, "registrants");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMediaCover = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaCover");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final EventEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpBeginTime;
            if (_cursor.isNull(_cursorIndexOfBeginTime)) {
              _tmpBeginTime = null;
            } else {
              _tmpBeginTime = _cursor.getString(_cursorIndexOfBeginTime);
            }
            final Integer _tmpQuota;
            if (_cursor.isNull(_cursorIndexOfQuota)) {
              _tmpQuota = null;
            } else {
              _tmpQuota = _cursor.getInt(_cursorIndexOfQuota);
            }
            final String _tmpRegistrants;
            if (_cursor.isNull(_cursorIndexOfRegistrants)) {
              _tmpRegistrants = null;
            } else {
              _tmpRegistrants = _cursor.getString(_cursorIndexOfRegistrants);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpMediaCover;
            if (_cursor.isNull(_cursorIndexOfMediaCover)) {
              _tmpMediaCover = null;
            } else {
              _tmpMediaCover = _cursor.getString(_cursorIndexOfMediaCover);
            }
            final String _tmpLink;
            if (_cursor.isNull(_cursorIndexOfLink)) {
              _tmpLink = null;
            } else {
              _tmpLink = _cursor.getString(_cursorIndexOfLink);
            }
            _result = new EventEntity(_tmpId,_tmpName,_tmpOwnerName,_tmpBeginTime,_tmpQuota,_tmpRegistrants,_tmpDescription,_tmpMediaCover,_tmpLink);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllFavoriteEvents(final Continuation<? super List<EventEntity>> continuation) {
    final String _sql = "SELECT * FROM EventEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EventEntity>>() {
      @Override
      public List<EventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfBeginTime = CursorUtil.getColumnIndexOrThrow(_cursor, "beginTime");
          final int _cursorIndexOfQuota = CursorUtil.getColumnIndexOrThrow(_cursor, "quota");
          final int _cursorIndexOfRegistrants = CursorUtil.getColumnIndexOrThrow(_cursor, "registrants");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMediaCover = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaCover");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final List<EventEntity> _result = new ArrayList<EventEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EventEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpBeginTime;
            if (_cursor.isNull(_cursorIndexOfBeginTime)) {
              _tmpBeginTime = null;
            } else {
              _tmpBeginTime = _cursor.getString(_cursorIndexOfBeginTime);
            }
            final Integer _tmpQuota;
            if (_cursor.isNull(_cursorIndexOfQuota)) {
              _tmpQuota = null;
            } else {
              _tmpQuota = _cursor.getInt(_cursorIndexOfQuota);
            }
            final String _tmpRegistrants;
            if (_cursor.isNull(_cursorIndexOfRegistrants)) {
              _tmpRegistrants = null;
            } else {
              _tmpRegistrants = _cursor.getString(_cursorIndexOfRegistrants);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpMediaCover;
            if (_cursor.isNull(_cursorIndexOfMediaCover)) {
              _tmpMediaCover = null;
            } else {
              _tmpMediaCover = _cursor.getString(_cursorIndexOfMediaCover);
            }
            final String _tmpLink;
            if (_cursor.isNull(_cursorIndexOfLink)) {
              _tmpLink = null;
            } else {
              _tmpLink = _cursor.getString(_cursorIndexOfLink);
            }
            _item = new EventEntity(_tmpId,_tmpName,_tmpOwnerName,_tmpBeginTime,_tmpQuota,_tmpRegistrants,_tmpDescription,_tmpMediaCover,_tmpLink);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
